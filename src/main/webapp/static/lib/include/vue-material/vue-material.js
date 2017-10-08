/* global Vue, VueMaterial */

(function () {
    const bus = new Vue();

    Object.defineProperties(Vue.prototype, {
        $$: {
            get() {
                return $(this.$el);
            }
        },
        $$bus: {
            get() {
                return bus;
            }
        }
    });

    Vue.use(VueMaterial);
})();
