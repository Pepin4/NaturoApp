// File: static/js/appointments.js
$(document).ready(function() {
    $('#appointmentsTable').DataTable({
        "paging": true,
        "searching": true,
        "lengthChange": true,
        "pageLength": 10,
        "ordering": true,
        "info": true  // Pagination
    });
});
