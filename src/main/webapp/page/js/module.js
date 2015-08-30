/**
 * Created by xiangkui on 2015/2/20.
 */

var urls = {
  material:{
      list:"/material/list.ajax",
      save:"/material/save.ajax",
      edit:"/material/edit.html",
      delete:"/material/delete.ajax"
  },
    product:{
        get: "/product/get.ajax",
        list:"/product/list.ajax",
        add : "/product/add.html",
        save:"/product/save.ajax",
        edit:"/product/edit.html",
        delete:"/product/delete.ajax",
        listMaterial:"/product/listMaterialByProductId.ajax",
        addMaterial:"/product/addMaterialItemByProductId.ajax",
        deleteMaterial:"/product/deleteMaterialItemByProductId.ajax",
        updateMaterial:"/product/updateMaterialItemByProductId.ajax"
    },
    manufacturer:{
        index:"/basicInfo/manufacturer.html",
        list:"/manufacturer/list.ajax",
        add : "/manufacturer/add.html",
        save:"/manufacturer/save.ajax",
        edit:"/manufacturer/edit.html",
        delete:"/manufacturer/delete.ajax"
    },
    user:{
        index:"/basicInfo/user.html",
        list:"/user/list.ajax",
        add : "/user/add.html",
        save:"/user/save.ajax",
        edit:"/user/edit.html",
        delete:"/user/delete.ajax"
    },
    //客户订单相关
    userorder:{
        index:"/userorder/index.html",
        list:"/userorder/list.ajax",
        add : "/userorder/add.html",
        save:"/userorder/save.ajax",
        edit:"/userorder/edit.html",
        delete:"/userorder/delete.ajax",
        addProduct:"/userorder/addProduct.ajax",
        listProduct:"/userorder/listProduct.ajax"
    },
    chudan:{
        jianhuodan_result:"/chudan/jianhuodan_result.ajax",
        jinhuodan_manufacturer:"/chudan/jianhuodan_manufacturer.ajax"
    },
    order:{
        list:"/order/list.ajax",
        edit:"/order/edit.html",
        delete:"/order/delete.ajax"
    }

};