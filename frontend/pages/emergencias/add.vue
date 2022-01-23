<template>
    <v-card>
        <v-card-text>    
            <v-form @submit.prevent="addEmergencia"
                ref="form"
                v-model="valid"
                lazy-validation
            >
                <h1>Crear Emergencia</h1>
                <v-row class="mt-4">
                    <v-col cols="12" md="6" lg="4">
                        <v-text-field filled
                            label="Nombre"
                            v-model="emergencia.name"
                            :rules="[v => !!v || 'Nombre es requerido']"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6" lg="4">
                        <v-text-field filled
                            label="Descripcion"
                            v-model="emergencia.descrip"
                            :rules="[v => !!v || 'Descripcion es requerido']"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6" lg="4">
                        <v-menu
                            ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            :return-value.sync="emergencia.finicio"
                            transition="scale-transition"
                            offset-y
                            min-width="auto"
                        >
                            <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                    v-model="emergencia.finicio"
                                    label="Fecha de inicio"
                                    filled
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"
                                    :rules="[v => !!v || 'La fecha de inicio es requerida']"
                                ></v-text-field>
                            </template>
                            <v-date-picker
                                v-model="emergencia.finicio"
                                no-title
                                scrollable
                            >
                                <v-spacer></v-spacer>
                                <v-btn
                                    text
                                    color="primary"
                                    @click="menu = false"
                                >
                                    Cancel
                                </v-btn>
                                <v-btn
                                    text
                                    color="primary"
                                    @click="$refs.menu.save(emergencia.finicio)"
                                >
                                OK
                                </v-btn>
                            </v-date-picker>
                        </v-menu>
                    </v-col>
                    <v-col cols="12" md="6" lg="4">
                        <v-menu
                            ref="menu2"
                            v-model="menu2"
                            :close-on-content-click="false"
                            :return-value.sync="emergencia.ffin"
                            transition="scale-transition"
                            offset-y
                            min-width="auto"
                        >
                            <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                    v-model="emergencia.ffin"
                                    label="Fecha de fin"
                                    filled
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"
                                    :rules="[v => !!v || 'La fecha de fin es requerida']"
                                ></v-text-field>
                            </template>
                            <v-date-picker
                                v-model="emergencia.ffin"
                                no-title
                                scrollable
                            >
                                <v-spacer></v-spacer>
                                <v-btn
                                    text
                                    color="primary"
                                    @click="menu2 = false"
                                >
                                    Cancel
                                </v-btn>
                                <v-btn
                                    text
                                    color="primary"
                                    @click="$refs.menu2.save(emergencia.ffin)"
                                >
                                OK
                                </v-btn>
                            </v-date-picker>
                        </v-menu>
                    </v-col>
                    <v-col cols="12" md="6" lg="4">
                        <v-select filled
                            :items="instituciones"
                            v-model="emergencia.id_institucion"
                            label="Institucion"
                            item-text="name"
                            item-value="id"
                            :rules="[v => !!v || 'La institucion es requerida']"
                        ></v-select>
                    </v-col>
                    <v-col cols="12" md="6" lg="4">
                        <v-select filled
                            :items="habilidades"
                            v-model="requisitos"
                            label="Requisitos"
                            item-text="descrip"
                            item-value="id"
                            multiple
                            required
                            :rules="[(v) =>  v.length > 0 || 'Los requisitos son requeridos']"
                        ></v-select>
                    </v-col>
                    
                    <!-- POSTGIS -->
                    <v-col cols="12" md="6" lg="4">
                        <v-text-field filled
                            label="Longitud"
                            type="number"
                            v-model="emergencia.longitude"
                            :rules="[v => !!v || 'Longitud es requerido']"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6" lg="4">
                        <v-text-field filled
                            label="Latitud"
                            type="number"
                            v-model="emergencia.latitude"
                            :rules="[v => !!v || 'Latitud es requerido']"
                        ></v-text-field>
                    </v-col>
                </v-row>
                
                <v-card-actions>    
                    <v-btn
                        color="primary"
                        tile
                        class="pa-5"
                        @click="addEmergencia"
                    >
                        Crear
                    </v-btn>
                </v-card-actions>
            </v-form>
        </v-card-text>
    </v-card>
</template>

<script>
export default {
    components: {},
    props: {},
    data: () => ({
        emergencia: {},
        requisitos: [],
        instituciones: [],
        habilidades: [],
        menu: false,
        menu2: false,
        valid: true,
    }),
    mounted() {
        this.getAllInstituciones();
        this.getAllHabilidades();
    },
    methods: {
        async getAllInstituciones(){
            try {
                let response = await this.$axios.get(`/instituciones`);
                this.instituciones  = response.data;
                console.log(response)
            } catch (error) {
                console.log(e);
            }
        },

        async getAllHabilidades() {
            try {
                let response = await this.$axios.get(`/habilidades`);
                this.habilidades = response.data;
                console.log(response)
            } catch (error) {
                console.log(e);
            }
        },

        addEmergencia() {
            if(this.$refs.form.validate()){
                this.emergencia.habilidades = this.requisitos;
                this.$axios.post('/emergencias/createVue', this.emergencia)
                    .then(response => {
                        console.log(response);
                        if(response.data.id){
                            this.$refs.form.reset();
                            this.$router.push('/emergencias/'+response.data.id);
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
        }
    },
}
</script>

<style>

</style>