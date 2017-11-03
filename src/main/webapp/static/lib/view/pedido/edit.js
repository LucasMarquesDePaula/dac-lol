/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(() => {
    const app = new Vue({
        el: "#app",
        data(){
            return {
                tipoFiltro: "pda"
            }
        },
        methods: {
            prevPage(limit, offset, count) {
                setTimeout(() => {
                    const param = $.deparam(location.search.substring(1));
                    param.offset = offset - limit <= 0 ? 0 : offset - limit;
                    location.search = $.param(param);
                }, 500);
            },
            nextPage(limit, offset, count) {
                setTimeout(() => {
                    const param = $.deparam(location.search.substring(1));
                    param.offset = offset + limit > count ? count : offset + limit;
                    location.search = $.param(param);
                }, 500);
            },
            onSort(sort) {
                setTimeout(() => {
                    const param = $.deparam(location.search.substring(1));
                    param.sortField = sort.name;
                    param.sortDirection = sort.type;
                    location.search = $.param(param);
                }, 500);
            }
        }
    });
})();

