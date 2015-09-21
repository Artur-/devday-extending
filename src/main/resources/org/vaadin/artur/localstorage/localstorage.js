window.org_vaadin_artur_localstorage_LocalStorageExtension  = function() {
    var rpcProxy = this.getRpcProxy();

    this.registerRpc({
    		get: function(key) {
    			var value = window.localStorage.getItem(key);
    			rpcProxy.reportValue(key,value);
    		},
    		set: function(key, value) {
    			window.localStorage.setItem(key,value);
    		}
    });
	
}