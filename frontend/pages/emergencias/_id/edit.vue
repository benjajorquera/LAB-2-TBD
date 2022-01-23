<template>
    <h1>edit Emergencia {{id}}</h1>
</template>

<script>
export default {
    components: {},
    props: {},
    data: () => ({
        id: ""
    }),
    mounted() {
        this.id = this.$route.params.id;
    },
    methods: {

    },
}
</script>

<style>

</style>