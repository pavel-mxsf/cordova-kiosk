cordova.define("kiosk", function (require, exports, module) {
    var exec = require('cordova/exec');

    var Kiosk = function () {

    };

    Kiosk.prototype.hideBars = function () {
        exec(success, error, "cordovaKiosk", "hideBars", []);
    };

    Kiosk.prototype.showBars = function () {
        exec(success, error, "cordovaKiosk", "showBars", []);
    };

    var kiosk = new Kiosk();
    module.exports = kiosk;
});