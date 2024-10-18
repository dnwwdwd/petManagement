<template>
  <div style="max-width: 80%">
    <div style="margin-bottom: 15px; display: flex; justify-content: center">
      <a-button style="color: #67C23A" @click="showModal">我要捐赠宠物</a-button>
      <a-modal v-model:open="open" title="捐赠宠物" @ok="handleOk" cancelText="取消" okText="确认捐赠">
        宠物名称：<a-input v-model:value="formModal.petName" class="a-input"/>
        年龄：<a-input v-model:value="formModal.age" class="a-input"/>
        性别：<a-input v-model:value="formModal.sex" class="a-input"/>
        描述：<a-input v-model:value="formModal.description" class="a-input"/>
        状态：<a-input v-model:value="formModal.state" class="a-input"/>
        <div style="margin-top: 10px; margin-bottom: 10px">
          是否绝育：
          <a-select
              v-model:value="formModal.isSterilized"
              style="width: 120px"
          >
            <a-select-option value="0">未绝育</a-select-option>
            <a-select-option value="1">已绝育</a-select-option>
          </a-select>
          是否接种疫苗：
          <a-select
              v-model:value="formModal.isVaccination"
              style="width: 120px"
          >
            <a-select-option value="0">未接种</a-select-option>
            <a-select-option value="1">已接种</a-select-option>
          </a-select>
        </div>
        宠物类型：
        <a-select
            v-model:value="formModal.type"
            style="width: 120px"
        >
          <a-select-option value="0">猫</a-select-option>
          <a-select-option value="1">狗</a-select-option>
          <a-select-option value="2">仓鼠</a-select-option>
        </a-select>
      </a-modal>
    </div>
    <a-card hoverable style="width: 220px" v-for="pet in petList" :key="pet.id" @click="() => router.push(`/adopt/${pet.id}`)">
      <template #cover>
        <img
            alt="喵喵"
            :src="pet.avatarUrl"
            style="height: 180px"
        />
      </template>
      <a-card-meta
          :title="pet.petName"
          v-html="`<span style='color: gray;'>${pet.petName} 年龄：${pet.age} <br> 性别：${pet.sex} <br> ${pet.isVaccination === 0 ? '未接种疫苗' : '已接种疫苗'} <br> ${pet.isSterilized === 0 ? '已绝育' : '未绝育'}</span>`"
      >
      </a-card-meta>
    </a-card>
  </div>
</template>

<script setup lang="js">
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import myAxios from "../../plugins/myAxios.js";

const router = useRouter();

const petName = ref('');

const petList = ref([]);
const open = ref(false);

const formModal = ref({
  petName: '',
  age: '',
  sex: '',
  state: '',
  isSterilized: '',
  isVaccination: '',
  description: '',
  type: '',
});

const showModal = () => {
  open.value = true;
};

const handleOk = async () => {
  console.log('Submitted data:', formModal.value);
  console.log('Submitted data:', petName.value);
  const res = await myAxios.post('/donate/pet', formModal.value);
  if (res.code === 0) {
    open.value = false;
    loadData();
  }
};

const loadData = async () => {
  const res = await myAxios.get('/donate/pet/list');
  if (res.code === 0) {
    petList.value = res.data;
  }
};

onMounted(async () => {
  loadData();
});

</script>

<style>

</style>
