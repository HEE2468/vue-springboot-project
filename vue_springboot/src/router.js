import Vue from "vue";
import VueRouter from "vue-router";
import Login from "./views/Login";
import Index from "./views/Index";
import Welcome from "./views/Welcome";
import UserList from "./views/user/UserList"

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "login",
        component: Login
    },
    {
        path: "/index",
        name: "index",
        component: Index,
        redirect:'/welcome',
        children:[
            {
                path:'/welcome',
                component:Welcome
            },{
                path:'/users',
                component:UserList
            }
         ]
    }
];

const router = new VueRouter({
    routes
});

export default router;