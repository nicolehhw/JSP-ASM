$(document).ready(function () {
    // gym center management table
    $("#gym-center-m-table tfoot th").each(function () {
        $(this).html('<input type="text" placeholder="Search" />');
    });

    const gym_center_m_table = $("#gym-center-m-table").DataTable({
        dom: '<"topbar"lf>Bt<"pagaination"ip>',
        responsive: true,
        buttons: [
            {
                text: "Add Gym Center",
                className: "btn btn-primary",
                action: function (e, dt, node, config) {
                    window.location.href = "./editGymCenterForm.jsp";
                },
            },
            {
                extend: "csv",
                className: "btn btn-primary",
            },
            {
                extend: "excel",
                className: "btn btn-primary",
            },
            {
                extend: "pdf",
                className: "btn btn-primary",
            },
        ],
        language: {
            lengthMenu: "_MENU_",
            info: "Showing _START_ to _END_ of _TOTAL_ booking records",
            eroRecords: "No matching booking records found",
            search: "_INPUT_",
            searchPlaceholder: "Enter keyword to search gym center",
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

    // user management table
    $("#user-m-table tfoot th").each(function () {
        $(this).html('<input type="text" placeholder="Search" />');
    });

    const user_m_table = $("#user-m-table").DataTable({
        dom: '<"topbar"lf>Bt<"pagaination"ip>',
        responsive: true,
        buttons: [
            {
                text: "Create User",
                className: "btn btn-primary",
                action: function (e, dt, node, config) {
                    window.location.href = "./editUserForm.jsp";
                },
            },
            {
                extend: "csv",
                className: "btn btn-primary",
            },
            {
                extend: "excel",
                className: "btn btn-primary",
            },
            {
                extend: "pdf",
                className: "btn btn-primary",
            },
        ],
        language: {
            lengthMenu: "_MENU_",
            info: "Showing _START_ to _END_ of _TOTAL_ booking records",
            eroRecords: "No matching booking records found",
            search: "_INPUT_",
            searchPlaceholder: "Enter keyword to search user",
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
        },
    });

    // gym center booking requests table
    $("#booking-records-table tfoot th").each(function () {
        $(this).html('<input type="text" placeholder="Search" />');
    });

    const booking_records_table = $("#booking-records-table").DataTable({
        dom: '<"topbar"lf>Bt<"pagaination"ip>',
        responsive: true,
        order: [[1, 'desc']],
        buttons: [
            {
                extend: "csv",
                className: "btn btn-primary",
            },
            {
                extend: "excel",
                className: "btn btn-primary",
            },
            {
                extend: "pdf",
                className: "btn btn-primary",
            },
        ],
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
        },
    });

    //change active tab
    $("#left-bar > a").each(function (index, value) {
        const url = window.location.pathname.split("/");
        const current = window.location.pathname.split("/")[url.length - 1];
        if ($(this).attr('href').includes(current)) {
            $(this).addClass("active");
            return;
        }
    });
});
