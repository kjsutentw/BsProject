
var add = require("./model01")
var Vue= require("./vue")

var Vue=new Vue({
    el:'#app-4',
    data:{
        msg:'你好呀，猪崽子!',
        result:0
    },
    methods:{
        add:function(){
            this.result =add(3,6)
        }
    }
});