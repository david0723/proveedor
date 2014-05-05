define(['model/proveedorModel'], function(proveedorModel) {
    App.Controller._ProveedorController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#proveedor').html());
            this.listTemplate = _.template($('#proveedorList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'proveedor-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'proveedor-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'proveedor-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'proveedor-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-proveedor-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'proveedor-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-proveedor-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-proveedor-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-proveedor-create', {view: this});
                this.currentProveedorModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-proveedor-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-proveedor-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-proveedor-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-proveedor-list', {view: this, data: data});
                var self = this;
				if(!this.proveedorModelList){
                 this.proveedorModelList = new this.listModelClass();
				}
                this.proveedorModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-proveedor-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'proveedor-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-proveedor-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-proveedor-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-proveedor-edit', {view: this, id: id, data: data});
                if (this.proveedorModelList) {
                    this.currentProveedorModel = this.proveedorModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-proveedor-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentProveedorModel = new this.modelClass({id: id});
                    this.currentProveedorModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-proveedor-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'proveedor-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-proveedor-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-proveedor-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-proveedor-delete', {view: this, id: id});
                var deleteModel;
                if (this.proveedorModelList) {
                    deleteModel = this.proveedorModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-proveedor-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'proveedor-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-proveedorForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-proveedor-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-proveedor-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-proveedor-save', {view: this, model : model});
                this.currentProveedorModel.set(model);
                this.currentProveedorModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-proveedor-save', {model: self.currentProveedorModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'proveedor-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({proveedors: self.proveedorModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({proveedor: self.currentProveedorModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ProveedorController;
});