define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/proveedorModel', 'controller/proveedorController'], function() {
    App.Component.ProveedorComponent = App.Component._CRUDComponent.extend({
        name: 'proveedor',
        model: App.Model.ProveedorModel,
        listModel: App.Model.ProveedorList,
        controller : App.Controller.ProveedorController
    });
    return App.Component.ProveedorComponent;
});