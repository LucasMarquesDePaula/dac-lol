(function () {
    var Vue = window.Vue;
    var VueMaterial = window.VueMaterial;
    Vue.use(VueMaterial);
    var app = new Vue({
        el: "#app",
        data: function () {
            return {
                msg: "Hello Vue"
            };
        },
        methods: {
            toggleSidenav: function () {
                this.$refs.sidenav.toggle();
            },
            open: function (ref) {
                console.log('Opened: ' + ref);
            },
            close: function (ref) {
                console.log('Closed: ' + ref);
            }
        }
    });
})();
