<template>
  <a-layout class="layout">
    <a-layout-header :style="{ background: '#fff', display: 'flex', alignItems: 'center' }">
      <img src="../assets/logo.png" alt="Logo" style="height: 40px; margin-right: 20px" />
      <span style="font-size: 15px; color: #1E90FF">江苏牧职宠物管理系统</span>
        <a-menu
            v-model:selectedKeys="selectedKeys"
            theme="light"
            mode="horizontal"
            :style="{ lineHeight: '64px' }"
        >
          <a-menu-item key="1">
            <router-link to="/">
              首页
            </router-link>
          </a-menu-item>
          <a-menu-item key="2">
            <router-link to="/pet/adopt">
              宠物领养
            </router-link>
          </a-menu-item>
          <a-menu-item key="3">
            <router-link to="/pet/forum">
              宠物论坛
            </router-link>
          </a-menu-item>
          <a-menu-item key="4">
            <router-link to="/pet/donate">
              宠物捐赠
            </router-link>
          </a-menu-item>
          <a-menu-item key="5">
            <router-link to="/activity">
              热门活动
            </router-link>
          </a-menu-item>
        </a-menu>
      </a-layout-header>
    <a-layout-content style="padding: 0 50px">
      <router-view />
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
  </a-layout>
</template>
<script lang="ts" setup>
import {ref} from 'vue';

const selectedKeys = ref<string[]>(['1']);



</script>

<style scoped>


</style>
