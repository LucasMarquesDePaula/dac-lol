(function () {
    var toolbar = new Vue({
        el: "#toolbar",
        methods: {
            toggleSidenavLeft: function () {
                this.$$bus.$emit("Toolbar.toggleSidenavLeft");
            }
        }
    });
})();