import Vue from "vue";
import VueRouter from "vue-router";
import Login from "./views/Login";


Vue.use(VueRouter);

/*export default new VueRouter({
    routes:[
        {
            path:"/",
            name:"Login",
            component:Login
        }
    ]
})*/

const routes = [
    {
        path: "/",
        name: "login",
        component: Login
    }
];

const router = new VueRouter({
    routes
});

export default router;