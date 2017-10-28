(() => {
    const app = new Vue({
        el: "#app",
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