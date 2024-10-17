<template>
  <div style="max-width: 80%; margin: auto;">
    <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="listData">
      <template #renderItem="{ item }">
        <a-list-item key="item.title">
          <template #extra>
            <img
                height="100px"
                alt="logo"
                :src="item.imgUrl"
            />
          </template>
          <a-list-item-meta :description="item.name">
            <template #title>
              <a :href="item.href">{{ item.user.nickname }}</a>
            </template>
            <template #avatar><a-avatar :src="item.user.avatarUrl" /></template>
          </a-list-item-meta>
          {{ item.description }}
        </a-list-item>
      </template>
    </a-list>
  </div>
</template>
<script lang="js" setup>
import {onMounted, ref} from "vue";
import myAxios from "../../plugins/myAxios.js";

const listData = ref([]);

const pagination = {
  onChange: (page) => {
    loadData(page);
  },
  pageSize: 3,
};

const loadData = async (page) => {
  const res = await myAxios.get('/pet/forum/list', {
    pageSize: pagination.pageSize,
    pageNum: page,
  });
  listData.value = res.data;
}

onMounted(async () => {
  loadData(1);
});

</script>