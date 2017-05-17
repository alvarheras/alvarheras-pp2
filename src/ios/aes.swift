import Foundation
import LocalAuthentication
import CryptoSwift
import MMDrawerController
import Alamofire

@objc(Aes) class Aes : CDVPlugin {
  

    func aesEncrypt(_ command: CDVInvokedUrlCommand) {

        let authenticationContext = LAContext()
        var pluginResult = CDVPluginResult(status: CDVCommandStatus_ERROR, messageAs: "Something went wrong")

        let data  = command.arguments[0] as AnyObject?

        guard let login = data?["login"] as! String? else {
          self.commandDelegate.send(pluginResult, messageAs: "Not available")
          return
        }
      
        do {
          try let callbackId = login.aesEncryptDell()
          pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Success")
          self.commandDelegate.send(pluginResult, callbackId:command.callbackId)
        } catch {
          self.commandDelegate.send(pluginResult, messageAs: "Not available")
          print("error)
        }

    }

    func aesEncryptDell() throws -> String {

        let keySt = "0add2dabb18204cca874ab56ed373cbdf1595bf1092dc3e574ab251e8ec4b56c"
        let iv: [UInt8] = [0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00]
        //pasamos el string a hexadecimal y array de bytes
        let data = keySt.dataFromHexadecimalString()
        //print("Data from hex String: \(data)")
        let count = data!.count / MemoryLayout<UInt8>.size
        // create an array of Uint8
        var key = [UInt8](repeating: 0, count: count)
        // copy bytes into array
        (data! as NSData).getBytes(&key, length:count * MemoryLayout<UInt8>.size)
        
        let data2 = self.data(using: String.Encoding.utf8)
        let enc = try AES(key: key, iv: iv, blockMode:.CBC, padding: PKCS7()).encrypt(data2!.bytes)
        let encData = Data(bytes: enc, count: enc.count)
        let aux:String = encData.hexadecimalString()
        //print("*** Array Bytes: \(key)")
        //print(data2!.arrayOfBytes())
        //print("*** Array Bytes encriptados: \(enc)")
        //print("*** Data from Array Bytes encriptados: \(encData)")
        //print(aux.toHexString())
        let result = String(aux.toHexString())
        return result!

    }
    
    func aesDecrypt(_ keySt: String, iv: [UInt8]) throws -> String {

        //pasamos el string a hexadecimal y array de bytes
        let data = keySt.dataFromHexadecimalString()
        //print("Data from hex String: \(data)")
        let count = data!.count / MemoryLayout<UInt8>.size
        // create an array of Uint8
        var key = [UInt8](repeating: 0, count: count)
        // copy bytes into array
        (data! as NSData).getBytes(&key, length:count * MemoryLayout<UInt8>.size)
        
        let data2 = self.data(using: String.Encoding.utf8)
        let dec = try AES(key: key, iv: iv, blockMode:.CBC, padding: PKCS7()).decrypt(data2!.bytes)
        let decData = Data(bytes: dec, count: dec.count)
        let result = NSString(data: decData, encoding: String.Encoding.utf8.rawValue)
        return String(result!)

    }
    
    func dataFromHexadecimalString() -> Data? {

        let data = NSMutableData(capacity: characters.count / 2)
        
        let regex = try! NSRegularExpression(pattern: "[0-9a-f]{1,2}", options: .caseInsensitive)
        regex.enumerateMatches(in: self, options: [], range: NSMakeRange(0, characters.count)) { match, flags, stop in
            let byteString = (self as NSString).substring(with: match!.range)
            let num = UInt8(byteString.withCString { strtoul($0, nil, 16) })
            data?.append([num], length: 1)
        }
        
        return data as Data?

    }
    
    func toHexString() -> String {

        var hex = "";
        for i in characters {
            let scalars = String(i).unicodeScalars
            let val = scalars[scalars.startIndex].value
            hex += String(format: "%02x", UInt32(UInt32(String(val))!))
        }
        return hex;

    }


    func hexadecimalString() -> String {

        var string = ""
        var byte: UInt8 = 0
        
        for i in 0 ..< count {
            copyBytes(to: &byte, from: NSMakeRange(i, 1).toRange()!)
            string += String(format: "%02x", byte)
        }
        
        return string
        
    }

}
