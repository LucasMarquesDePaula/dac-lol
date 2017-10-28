const app = new Vue({
    el: "#app",
    data() {
        return {
            foto: ""
        };
    },
    methods: {
        fotoClicked() {
            $("<input>", {type: "file", accept: "image/x-png,image/jpeg"})
                    .hide(0)
                    .appendTo(this.$$)
                    .change((event) => {
                        const file = event.currentTarget.files[0];
                        const reader = new FileReader();
                        reader.readAsDataURL(file);
                        reader.onload = () => {
                            this.foto = reader.result;
                            this.$$.find("[name=foto]").val(this.foto);
                        };
                    })
                    .click();
        }
    },
    mounted() {
        this.foto = this.$$.find("[name=foto]").val();
    }
});

