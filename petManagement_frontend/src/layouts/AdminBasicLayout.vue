<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <router-link to="/admin/petManagement">
            <FormOutlined />
            <span>宠物管理</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <router-link to="/admin/petAdoptManagement">
            <desktop-outlined />
            <span>宠物领养管理</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <router-link to="/admin/petDonateManagement">
            <desktop-outlined />
            <span>宠物捐赠管理</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="4">
          <router-link to="/admin/petForumManagement">
            <CommentOutlined />
            <span>宠物论坛管理</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="5">
          <router-link to="/admin/activityManagement">
            <VideoCameraAddOutlined />
            <span>热门活动管理</span>
          </router-link>
        </a-menu-item>
        <a-menu-item key="6">
          <router-link to="/admin/userManagement">
            <user-outlined />
            <span>用户管理</span>
          </router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0; height: 60px">
        <div style="display: flex; justify-content: space-between; align-items: center">
          <span>{{ title }}</span>
          <div style="margin-right: 30px">
            <a-dropdown >
              <a-avatar
                  shape="circle"
                  :src="user.avatarUrl"
              />
              <template #overlay>
                <a-menu>
                  <a-menu-item>
                    <router-link to="/admin/petManagement">后台管理</router-link>
                  </a-menu-item>
                  <a-menu-item>
                    <router-link to="/user/setting">个人设置</router-link>
                  </a-menu-item>
                  <a-menu-item>
                    <router-link to="/user/login">退出登录</router-link>
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
        </div>
      </a-layout-header>
      <a-layout-content style="margin: 0 16px">
        <router-view/>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import {
  VideoCameraAddOutlined,
  DesktopOutlined,
  UserOutlined,
  FormOutlined,
  CommentOutlined,
} from '@ant-design/icons-vue';
import {onMounted, ref} from 'vue';
import {useRoute} from "vue-router";
import {getCurrentUser} from "../services/user.js";

const collapsed = ref<boolean>(false);
const selectedKeys = ref<string[]>(['1']);


const route = useRoute();

const user = ref({});

onMounted(async () => {
  if (!route.path.includes('/user/login') && !route.path.includes('/user/register')) {
    const res = await getCurrentUser();
    if (res) {
      user.value = res;
    }
  }
});

</script>
<style scoped>
#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}
[data-theme='dark'] .site-layout .site-layout-background {
  background: #141414;
}
</style>
