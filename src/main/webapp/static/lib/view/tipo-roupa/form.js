const app = new Vue({
    el: "#app",
    data() {
        return {
            precoLavagem: null,
            prazoLavagem: null,
            precoLavagemMask: null,
            prazoLavagemMask: null
        }
    },
    watch: {
        prazoLavagemMask(value) {
            this.prazoLavagem = value
                    .replace("R$ ", "")
                    .replace(".", "")
                    .replace(",", ".") * 1;
        },
        precoLavagemMask(value) {
            this.precoLavagem = value
                    .replace("R$ ", "")
                    .replace(".", "")
                    .replace(",", ".") * 1;
            
            this.precoLavagem = Math.floor(this.precoLavagem);
        }
    },
    mounted() {
        this.precoLavagemMask = $("#precoLavagem").val();
        this.prazoLavagemMask = $("#prazoLavagem").val();
    }
});

