<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="tree" style="overridde: hidden" ></div>
<script>
	require([
    "dojo/ready", "dojo/_base/window", "dojo/store/Memory","dojo/store/Observable",
    "dijit/tree/ObjectStoreModel", "dijit/Tree"
], function(ready, win, Memory,Observable, ObjectStoreModel, Tree){

    // Create test store, adding the getChildren() method required by ObjectStoreModel
    myStore = new Memory({
        data:${jsonTree},
        getChildren: function(object){
            return this.query({parentId: object.id});
        }
    });
	
   // give store Observable interface so Tree can track updates
	myStore = new Observable(myStore);
 
    // Create the model
    myModel = new ObjectStoreModel({
        store: myStore,
        mayHaveChildren: function(object){
			// Normally the object would have some attribute (like a type) that would tell us if
			// it might have children.   But since our data is local we'll just check if there
			// actually are children or not.
			return this.store.getChildren(object).length > 0;
		},
        query: {id: 1}
    });

    // Create the Tree.   Note that all widget creation should be inside a dojo.ready().
    ready(function(){
         myTree = new Tree({
            model: myModel,
            getIconClass: function(/*dojo.store.Item*/ item, /*Boolean*/ opened){
                return (!item || this.model.mayHaveChildren(item)) ? (opened ? "dijitFolderOpened" : "dijitFolderClosed") : "dijitLeaf"
            }
        });
        myTree.on("click", function(object){
			loadDetail(object.id);
		}, true);
        myTree.placeAt("tree");
        myTree.startup();
    });
});
	require(["dijit/registry", "dijit/Menu", "dijit/MenuItem","dojo/aspect", "dojo/query!css2"], function(registry, Menu, MenuItem,aspect){
	    var menu = new Menu({
	        targetNodeIds: ["tree"],
	        selector: ".dijitTreeNode"
	    });
	    var item_add=new MenuItem({
	    	 label: "添加资源",
		     onClick: function(evt){
		            var node = this.getParent().currentTarget;
		            var tn = dijit.byNode(node);
		            loadSave(tn.item.id);
		            console.log("menu clicked for node id", tn.item.id);
		        }
		    });
	    var item_edit=new MenuItem({
	    	 label: "编辑资源",
		     onClick: function(evt){
		            var node = this.getParent().currentTarget;
		            var tn = dijit.byNode(node);
		            loadUpdate(tn.item.id);
		            console.log("menu clicked for node id", tn.item.id);
		        }
		    });
	    var item_del=new MenuItem({
	    	 label: "删除资源",
		     onClick: function(evt){
		            var node = this.getParent().currentTarget;
		            var treeNode = dijit.byNode(node);
		           // alert(treeNode);
		            //alert(treeNode.item);
		            //alert(myModel.mayHaveChildren(tn.item));
		            deleteResource(treeNode);
		            console.log("menu clicked for node id", treeNode.item.id);
		        }
		    });
	    menu.addChild(item_add);
	    menu.addChild(item_edit);
	    menu.addChild(item_del);
	   //	registry.byId("menu_item_del").set("disabled", true);
	});
	</script>