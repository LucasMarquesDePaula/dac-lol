(function () {
    var sidenavLeft = new Vue({
        el: "#sidenav-left",
        created: function () {
            this.$$bus.$on("Toolbar.toggleSidenavLeft", function () {
                sidenavLeft.$refs.self.toggle();
            });
        }
    });
})();