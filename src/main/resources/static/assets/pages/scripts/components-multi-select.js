var ComponentsDropdowns = function () {
   
    
    var handleBootstrapSelect = function() {
        $('.bs-select').selectpicker({
            iconBase: 'fa',
            tickIcon: 'fa-check'
        });
    }

    var handleMultiSelect = function () {
        $('#my_multi_select').multiSelect();
        $('#my_multi_select2').multiSelect({
            selectableOptgroup: true
        });
    }

    return {
        //main function to initiate the module
        init: function () {
            handleMultiSelect();
            handleBootstrapSelect();
        }
    };

}();

jQuery(document).ready(function() {
   ComponentsDropdowns.init(); 
});
