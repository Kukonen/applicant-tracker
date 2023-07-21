<script setup lang="ts">
import {ref} from "vue";
import {request} from "./helpers/request.ts";
import {convertCandidate} from "./helpers/candidateFormater.ts";

const personSnils = ref('');
const candidates = ref();

const search = async () => {
    const response = await request(`http://localhost:3030/api/v1/applicant/${personSnils.value}`).then(res => {
        candidates.value = convertCandidate(res)
    });
}

const columns = [
    {
        title: 'Университет',
        dataIndex: 'university',
        key: 'university',
        sorted: true,
        sorter: (value1, value2) => ('' + value1.attr).localeCompare(value2.attr)
    },
    {
        title: 'Направление',
        dataIndex: 'programName',
        key: 'programName',
        sorter: (value1, value2) => ('' + value1.attr).localeCompare(value2.attr)
    },
    {
        title: 'Форма обучения',
        dataIndex: 'educationForm',
        key: 'educationForm',
        sorter: (value1, value2) => ('' + value1.attr).localeCompare(value2.attr)
    },
    {
        title: 'Оплата',
        dataIndex: 'paymentType',
        key: 'paymentType',
        sorter: (value1, value2) => ('' + value1.attr).localeCompare(value2.attr)
    },
    {
        title: 'Баллы',
        dataIndex: 'score',
        key: 'score',
        sorter: (value1, value2) => value1 - value2
    },
    {
        title: 'Мест',
        dataIndex: 'programPlaces',
        key: 'programPlaces',
        sorter: (value1, value2) => value1 - value2
    },
    {
        title: 'Приоритет',
        dataIndex: 'priority',
        key: 'priority',
        sorter: (value1, value2) => value1 - value2
    },
    {
        title: 'Согласие',
        dataIndex: 'certificateSubmitted',
        key: 'certificateSubmitted',
        sorter: (value1, value2) => ('' + value1.attr).localeCompare(value2.attr)
    }
]

</script>

<template>
    <div id="search">
        <a-input-search
            v-model:value="personSnils"
            placeholder="снилс или фио"
            size="large"
            @search="search"
        >
            <template #enterButton>
                <a-button>Custom</a-button>
            </template>
        </a-input-search>
    </div>
    <a-table
        :columns="columns"
        :data-source="candidates"
    />
</template>

<style scoped lang="scss">
#search {
    width: 50%;
    margin: auto;
}
</style>
