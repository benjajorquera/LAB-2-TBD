<template>
    <div>
        <h1>Todas las Emergencias</h1>
        <v-row>
            <v-col cols="12" md="3" v-for="(emergencia, key) in emergencias" :key="key">
                <v-card>
                    <v-card-title>
                        <span class="headline">Emergencias {{emergencia.name}}</span>
                    </v-card-title>
                    <v-card-text>
                        <v-btn :to="`/emergencias/${emergencia.id}`">Ver detalle</v-btn>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </div>
</template>

<script>
export default {
    components: {},
    props: {},
    data: () => ({
        emergencias: [],
    }),
    mounted() {
        this.getAllEmergencias();
    },
    methods: {
        async getAllEmergencias(){
            try {
                let response = await this.$axios.get('/emergencias');
                this.emergencias  = response.data;
                console.log(response)
            } catch (error) {
                console.log(e);
            }
        }
    },
}
</script>

<style>

</style>