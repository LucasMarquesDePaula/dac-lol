(function () {
    var toolbar = new Vue({
        el: "#toolbar",
        methods: {
            toggleSidenavLeft() {
                this.$$bus.$emit("Toolbar.toggleSidenavLeft");
            },
            logout(basePath) {
                window.location = `/${basePath}/logout`;
            }
        }
    });
})();