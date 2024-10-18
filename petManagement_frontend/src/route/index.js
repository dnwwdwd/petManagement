import Index from '../views/Index.vue';
import PetDetail from "../views/Pet/PetDetail.vue";
import PetAdopt from "../views/Pet/PetAdopt.vue";
import PetAdoptDetail from "../views/Pet/PetAdoptDetail.vue";
import PetForum from "../views/Pet/PetForum.vue";
import PetForumDetail from "../views/Pet/PetForumDetail.vue";
import PetDonate from "../views/Pet/PetDonate.vue";
import Activity from "../views/Activity/Activity.vue";
import ActivityDetail from "../views/Activity/ActivityDetail.vue";
import UserSetting from "../views/User/UserSetting.vue";
import LoginPage from "../views/LoginPage.vue";
import RegisterPage from "../views/RegisterPage.vue";
import PetManagement from "../views/Admin/PetManagement.vue";

const routes = [
    {path: '/', component: Index, meta: { layout: 'basic' }},
    {path: '/pet/detail/:id', component: PetDetail, meta: { layout: 'basic' }},
    {path: '/pet/adopt', component: PetAdopt, meta: { layout: 'basic' }},
    {path: '/adopt/:id', component: PetAdoptDetail, meta: { layout: 'basic' }},
    {path: '/pet/:id', component: PetAdoptDetail, meta: { layout: 'basic' }},
    {path: '/pet/forum', component: PetForum, meta: { layout: 'basic' }},
    {path: '/pet/forum/detail/:id', component: PetForumDetail, meta: { layout: 'basic' }},
    {path: '/pet/donate', component: PetDonate, meta: { layout: 'basic' }},
    {path: '/activity', component: Activity, meta: { layout: 'basic' }},
    {path: '/activity/detail/:id', component: ActivityDetail, meta: { layout: 'basic' }},
    {path: '/user/setting', component: UserSetting, meta: { layout: 'basic' }},
    {path: '/user/login', component: LoginPage, meta: { layout: 'basic' }},
    {path: '/user/register', component: RegisterPage, meta: { layout: 'basic' }},
    {path: '/admin/petManagement', component: PetManagement, meta: { layout: 'admin' }},
]

export default routes;