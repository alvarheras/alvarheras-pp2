exports.defineAutoTests = function() {
  describe('aes Object', function () {
    it("should exist", function() {
      expect(window.aes).toBeDefined();
    });
  });
};
