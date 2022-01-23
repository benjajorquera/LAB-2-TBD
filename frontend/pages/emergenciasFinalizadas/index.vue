<template>
    <div>
        <h1>Emergencias Finalizadas</h1>
        <v-card class="mt-4">
            <v-card-title>
                <span class="headline">Datos de las emergencias</span>
            </v-card-title>
            <v-card-text>
                <v-simple-table dark>
                    <template v-slot:default>
                        <thead>
                            <tr>
                                <th class="text-left">Nombre</th>
                                <th class="text-left">Descripción</th>
                                <th class="text-left">Fecha Inicio</th>
                                <th class="text-left">Fecha Termino</th>
                                <th class="text-left">Tareas Totales</th>
                                <th class="text-left">Voluntarios Totales</th>
                                <th class="text-left">Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                                v-for="emergency in emergencias"
                                :key="emergency.id"
                            >
                                <td>{{ emergency.name }}</td>
                                <td>{{ emergency.descrip }}</td>
                                <td>{{ emergency.finicio }}</td>
                                <td>{{ emergency.ffin }}</td>
                                <td>{{ emergency.total_tareas }}</td>
                                <td>{{ emergency.total_voluntarios }}</td>
                                <td>Finalizada</td>
                            </tr>
                        </tbody>
                    </template>
                </v-simple-table>
            </v-card-text>
        </v-card>
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
        this.getEmergencias();
    },
    methods: {
        async getEmergencias(){
            try {
                let response = await this.$axios.get('/emergencias/finalizadas');
                this.emergencias  = response.data;
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