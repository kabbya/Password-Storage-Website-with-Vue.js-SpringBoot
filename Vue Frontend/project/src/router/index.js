import { createWebHistory, createRouter } from "vue-router";
import Home from "../components/Home.vue";
import Login from "../components/Login.vue";
import Register from "../components/Register.vue";

const Profile = () => import("../components/Profile.vue")

const routes =  [

  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/home",
    component: Home,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/register",
    component: Register,
  },
  
  {
    path: "/profile",
    name: "profile",
    // lazy-loaded
    component: Profile,
  },
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;