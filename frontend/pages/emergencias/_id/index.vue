<template>
    <div>
        <h1>Emergencia {{ emergencia.name }}</h1>
        <v-card class="mt-4">
            <v-card-title>
                <span class="headline">Datos de la emergencia</span>
            </v-card-title>
            <v-card-text>
                <v-container grid-list-md>
                    <v-layout row wrap>
                        <v-flex xs12 md6>
                            <v-text-field
                                readonly
                                filled
                                label="Nombre"
                                v-model="emergencia.name"
                            ></v-text-field>
                        </v-flex>
                        <v-flex xs12 md6>
                            <v-text-field
                                readonly
                                filled
                                label="Descripción"
                                v-model="emergencia.descrip"
                            ></v-text-field>
                        </v-flex>
                        <v-flex xs12 md6>
                            <v-text-field
                                readonly
                                filled
                                label="Fecha de creación"
                                v-model="emergencia.finicio"
                            ></v-text-field>
                        </v-flex>
                        <v-flex xs12 md6>
                            <v-text-field
                                readonly
                                filled
                                label="Fecha de termino"
                                v-model="emergencia.ffin"
                            ></v-text-field>
                        </v-flex>
                        <v-flex xs12 md6>
                            <v-text-field
                                readonly
                                filled
                                label="Total Volunatarios"
                                v-model="voluntarios"
                            ></v-text-field>
                        </v-flex>
                        <v-flex xs12 md6>
                            <v-text-field
                                readonly
                                filled
                                label="Estado Emergencia"
                                v-model="estado"
                            ></v-text-field>
                        </v-flex>
                    </v-layout>
                </v-container>
            </v-card-text>
            <v-card-title>
                Tareas
            </v-card-title>
            <v-card-text>
                <v-simple-table dark>
                    <template v-slot:default>
                        <thead>
                            <tr>
                                <th class="text-left">Name</th>
                                <th class="text-left">Descripción</th>
                                <th class="text-left">Fecha Inicio</th>
                                <th class="text-left">Fecha Termino</th>
                                <th class="text-left">Voluntarios inscritos</th>
                                <th class="text-left">Voluntarios requeridos</th>
                                <th class="text-left">Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                                v-for="task in tareas"
                                :key="task.id"
                            >
                                <td>{{ task.nombre }}</td>
                                <td>{{ task.descrip }}</td>
                                <td>{{ task.finicio }}</td>
                                <td>{{ task.ffin }}</td>
                                <td>{{ task.cantVolInscritos }}</td>
                                <td>{{ task.cantVolRequeridos }}</td>
                                <td>{{ getStatus(task.idEstado) }}</td>                                
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
        id: "",
        emergencia: {},
        voluntarios: 0,
        tareas: [],
        estado: "",
    }),
    mounted() {
        this.id = this.$route.params.id;
        this.getEmergencia();
        this.getVoluntarios();
        this.getTareas();
    },
    methods: {
        async getEmergencia(){
            try {
                let response = await this.$axios.get(`/emergencias/${this.id}`);
                this.emergencia  = response.data;
                console.log(response)
            } catch (error) {
                console.log(e);
            }
        },
        
        async getVoluntarios(){
            try {
                let response = await this.$axios.get(`/tareas/voluntarios/${this.id}`);
                if(response.data){
                    this.voluntarios  = response.data;
                }
                console.log('Voluntarios:', response.data)
            } catch (error) {
                console.log(e);
            }
        },

        async getTareas(){
            try {
                let response = await this.$axios.get(`/tareas/emergencia/${this.id}`);
                this.tareas  = response.data;
                console.log(response.data)

                this.updateEstado();
            } catch (error) {
                console.log(e);
            }
        },

        updateEstado(){
            const sizeTareas = this.tareas.length;
            // suma los estados de las tareas
            let suma = 0;
            for (let i = 0; i < sizeTareas; i++) {
                suma += this.tareas[i].idEstado;
            }
            // si la suma es igual a 0, entonces esta asignada
            if (suma === 0) {
                this.estado = "Asignada";
            } else {
                // contar cuantos estados de las tareas son igual a 3
                let contador = 0;
                for (let i = 0; i < sizeTareas; i++) {
                    if (this.tareas[i].idEstado === 2) {
                        contador++;
                    }
                }
                // si la cantidad de tareas completadas (3) es igual a la cantidad de tareas, entonces esta completada
                if (contador === sizeTareas) {
                    this.estado = "Completada";
                } else {
                    this.estado = "En proceso";
                }
            }
        },

        getStatus(s) {
            switch (s) {
                case 0:
                    return "Asignada";
                case 1:
                    return "En proceso";
                case 2:
                    return "Completada";
                default:
                    return "Desconocido";
            }
        },
    },
}
</script>

<style>

</style>