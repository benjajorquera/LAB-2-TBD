<template>
    <div>
        <h1>Todos los voluntarios</h1>
        <v-card class="mt-4">
            <v-card-title>
                <span class="headline">Datos de los voluntarios</span>
            </v-card-title>
            <v-card-text>
                <template>
                    <v-data-table
                        :headers="headers"
                        :items="voluntarios"
                        :items-per-page="10"
                        :loading="loading"
                        loading-text="Loading... Please wait"
                    ></v-data-table>
                </template>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>
export default {
    components: {},
    props: {},
    data: () => ({
        voluntarios: [],
        headers: [
            { text: 'ID', align: 'start', value: 'id',},
            { text: 'Nombre', value: 'name',},
            { text: 'Fecha Nacimiento', value: 'fnacimiento' },
            { text: 'Longitud', value: 'longitude' },
            { text: 'Latitud', value: 'latitude' },
        ],
        loading: true,
    }),
    mounted() {
        this.getVoluntarios();
    },
    methods: {
        async getVoluntarios(){
            try {
                let response = await this.$axios.get('/voluntarios');
                this.voluntarios  = response.data;
                this.loading = false;
                console.log(response)
            } catch (error) {
                console.log(e);
            }
        },
    },
}
</script>

<style>

</style>