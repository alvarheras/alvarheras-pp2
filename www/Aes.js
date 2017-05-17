function aes() {
}

aes.prototype.aesEncrypt = function (params , successCallback, errorCallback) {
  cordova.exec(
    successCallback,
    errorCallback,
    "Aes",
    "aesEncrypt",
    [ params ]
  );
};

aes = new aes();
module.exports = aes;
