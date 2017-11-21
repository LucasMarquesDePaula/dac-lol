const app = new Vue({
    el: "#app",
    data() {
        return {
            model: {
                cpf: "",
                email: ""
            }
        }
    },
    validations: {
        model: {
            cpf: {
                required: validators.cpf
            },
            email: {
                email: validators.email,
                required: validators.required
            }
        }
    },
    methods: {
        submit(event) {
            this.$v.model.$touch()
            if (this.$v.model.$error) {
                event.preventDefault()
            }
        }
    }
});

