define([], function() {
    App.Model._ProveedorModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'email' : ''
 ,  
		 'telefono' : ''
 ,  
		 'direccion' : ''
 ,  
		 'observaciones' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
         return this.get(name);
        }
    });

    App.Model._ProveedorList = Backbone.Collection.extend({
        model: App.Model._ProveedorModel,
        initialize: function() {
        }

    });
    return App.Model._ProveedorModel;
});