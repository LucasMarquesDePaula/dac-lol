(function () {
    var toolbar = new Vue({
        el: "#toolbar",
        methods: {
            toggleSidenavLeft() {
                this.$$bus.$emit("Toolbar.toggleSidenavLeft");
            },
            logout() {
                window.location = [
                    window.location.origin,
                    location.pathname.split("/")[1],
                    "logout"
                ].join("/");
            }
        }
    });
})();