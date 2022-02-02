import { createWebHistory, createRouter } from "vue-router";
import Home from "../components/Home.vue";
import Login from "../components/Login.vue";
import Register from "../components/Register.vue";

const Profile = () => import("../components/Profile.vue")
const AllPasswords = () => import("../components/AllPasswords.vue")
const AddPassword = () => import("../components/AddPassword.vue")

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

  {
    path: "/passwords",
    name: "all-passwords",
    // lazy-loaded
    component: AllPasswords,
  },

  {
    path: "/add",
    name: "add-password",
    component: AddPassword,
  },
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;