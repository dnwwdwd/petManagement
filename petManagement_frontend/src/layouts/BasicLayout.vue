<template>
  <a-layout class="layout">
    <a-layout-header :style="{ background: '#fff', display: 'flex', alignItems: 'center', marginBottom: '24px' }">
      <img src="../assets/logo.png" alt="Logo" style="height: 40px; margin-right: 20px" />
      <span style="font-size: 15px; color: #1E90FF">江苏牧职宠物管理系统</span>
      <!-- 动态高亮菜单项 -->
      <a-menu
          :selectedKeys="selectedKeys"
          theme="light"
          mode="horizontal"
          :style="{ lineHeight: '64px', flexGrow: 1 }"
      >
        <a-menu-item key="1">
          <router-link to="/">首页</router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <router-link to="/pet/adopt">宠物领养</router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <router-link to="/pet/forum">宠物论坛</router-link>
        </a-menu-item>
        <a-menu-item key="4">
          <router-link to="/pet/donate">宠物捐赠</router-link>
        </a-menu-item>
        <a-menu-item key="5">
          <router-link to="/activity">热门活动</router-link>
        </a-menu-item>
      </a-menu>

      <a-dropdown style="margin-left: auto">
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
    </a-layout-header>

    <a-layout-content style="padding: 0 50px; height: 540px">
      <router-view />
    </a-layout-content>

    <a-layout-footer style="text-align: center">
      Designed by 叶欣然
    </a-layout-footer>
  </a-layout>
</template>

<script lang="js" setup>
import {onMounted, ref, watchEffect} from 'vue';
import {useRoute} from 'vue-router';
import {getCurrentUser} from "../services/user.js";

const selectedKeys = ref([]);
const route = useRoute();

const user = ref({});

// 使用 watchEffect 来监听路由变化并设置选中菜单项
watchEffect(() => {
  // 根据当前路由的 path 来设置选中的菜单项
  switch (route.path) {
    case '/':
      selectedKeys.value = ['1'];
      break;
    case '/pet/adopt':
      selectedKeys.value = ['2'];
      break;
    case '/pet/forum':
      selectedKeys.value = ['3'];
      break;
    case '/pet/donate':
      selectedKeys.value = ['4'];
      break;
    case '/activity':
      selectedKeys.value = ['5'];
      break;
    default:
      // 如果没有匹配到任何菜单项，清空选中项
      selectedKeys.value = [];
  }
});

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

</style>
