$(document).ready(function () {
    $("#notificaiton-table tfoot th").each(function () {
        $(this).html('<input type="text" placeholder="Search" />');
    });

    $("#notificaiton-table").DataTable({
        dom: '<"topbar"lf>t<"pagaination"ip>',
        responsive: true,
        order: [[0, 'desc']],
        language: {
            lengthMenu: "_MENU_",
            info: "Showing _START_ to _END_ of _TOTAL_ booking records",
            eroRecords: "No matching booking records found",
            search: "_INPUT_",
            searchPlaceholder: "Enter keyword to search booking records",
        },
        initComplete: function () {
            // footer search
            this.api()
                    .columns()
                    .every(function () {
                        var that = this;

                        $("input", this.footer()).on("keyup change clear", function () {
                            if (that.search() !== this.value) {
                                that.search(this.value).draw();
                            }
                        });
                    });
        }
    });
});


