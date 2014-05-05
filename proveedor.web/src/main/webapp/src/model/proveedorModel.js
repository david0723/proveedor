define(['model/_proveedorModel'], function() {
    App.Model.ProveedorModel = App.Model._ProveedorModel.extend({

    });

    App.Model.ProveedorList = App.Model._ProveedorList.extend({
        model: App.Model.ProveedorModel
    });

    return  App.Model.ProveedorModel;

});