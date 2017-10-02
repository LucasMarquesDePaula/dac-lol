const app = new Vue({
  el: "#app",
  data() {
	  return {
		  foto: null,
		  fotoSrc: null
	  };
  },
  computed: {

  },
  watch: {
	  foto() {
		console.log(this.foto);
		const file = this.$el.querySelector("input[type=file]").files[0];
		const reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = () => {
			this.fotoSrc = reader.result;
		};
	  }
  }
});

