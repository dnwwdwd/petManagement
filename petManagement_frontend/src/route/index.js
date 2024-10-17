import Index from '../views/Index.vue';
import PetDetail from "../views/Pet/PetDetail.vue";
import PetAdopt from "../views/Pet/PetAdopt.vue";
import PetAdoptDetail from "../views/Pet/PetAdoptDetail.vue";
import PetForum from "../views/Pet/PetForum.vue";
import PetDonate from "../views/Pet/PetDonate.vue";

const routes = [
    {path: '/', component: Index},
    {path: '/pet/detail/:id', component: PetDetail},
    {path: '/pet/adopt', component: PetAdopt},
    {path: '/adopt/:id', component: PetAdoptDetail},
    {path: '/pet/:id', component: PetAdoptDetail},
    {path: '/pet/forum', component: PetForum},
    {path: '/pet/donate', component: PetDonate},
]

export default routes;