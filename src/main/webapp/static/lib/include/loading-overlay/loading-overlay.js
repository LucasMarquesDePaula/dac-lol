/* global Vue */

(function () {
    var loadingOverlay = new Vue({
        el: "#loading-overlay",
        data: function () {
            return {
                hidden: false
            };
        },
        mounted: function () {
            setTimeout(function () {
                loadingOverlay.hidden = true;
            }, 1000);
        }
    });
})();
