<template>
    <v-form ref="form" @submit.prevent="submitHandler" v-model="valid" :lazy-validation="lazy">
        <v-card class="pa-2">
            <v-card-text>
                <div>
                    <v-autocomplete
                            v-model="getInfo.city"
                            :items="options"
                            dense
                            outlined
                            label="Выберите город"
                            placeholder="Шымкент"
                            :rules="cityRules"
                            required
                    ></v-autocomplete>
                </div>

                <div>
                    <v-text-field
                            ref="title"
                            label="Адрес"
                            placeholder="просп. Абая 151"
                            v-model="getInfo.address"
                            required
                            :rules="addressRules"
                    >
                    </v-text-field>
                </div>

                <div class="price-for-month">
                    <img src="/static/img/tenge.png" alt="" id="price-image">
                    <v-text-field
                            v-model="getInfo.price"
                            label="Цена за Месяц"
                            placeholder="0"
                            required
                            :rules="priceRules"
                    ></v-text-field>
                </div>

                <div>
                    <header>Язык обучения</header>
                    <v-radio-group class="ma-1"
                                   v-model="getInfo.language"
                                   :rules="languageRules"
                                   required
                    >
                        <v-radio
                                v-for="n in languages"
                                :key="n"
                                :label="`${n}`"
                                :value="n"
                        ></v-radio>
                    </v-radio-group>
                </div>

                <div>
                    <span class="label">Клювевые слова по которым студенты смогут найти ваш курс</span>
                    <v-combobox
                            v-model="getInfo.keyWords"
                            chips
                            clearable
                            label="key words"
                            multiple
                            prepend-icon="filter_list"
                            solo
                            append-icon=""
                    >
                        <template v-slot:selection="{ attrs, item, select, selected }">
                            <v-chip
                                    v-bind="attrs"
                                    :input-value="selected"
                                    close
                                    @click="select"
                                    @click:close="remove(item)"
                            >
                                <strong>{{ item }}</strong>&nbsp;
                            </v-chip>
                        </template>
                    </v-combobox>

                </div>
            </v-card-text>
            <v-card-actions>
                <v-btn color="error" text @click="resetForm">Reset Form</v-btn>
                <v-spacer></v-spacer>
                <v-btn color="primary" text type="submit" :disabled="!valid">Submit</v-btn>
                <v-btn v-if="getInfo.id" class="ma-2" tile color="indigo" dark @click="finish">Закончить!</v-btn>
            </v-card-actions>

        </v-card>
    </v-form>

</template>

<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex';

    export default {
        name: "SetCourseInfo",
        components: {},
        props: [
            'changeCourseStage'
        ],
        data: function () {
            return {
                submitted: false,
                options: ["Алма-Ата (Алматы)",
                    "Нур-Султан",
                    "Шымкент",
                    "Актобе",
                    "Караганда",
                    "Тараз",
                    "Павлодар",
                    "Усть-Каменогорск",
                    "Семей",
                    "Атырау",
                    "Костанай",
                    "Кызылорда",
                    "Уральск",
                    "Петропавловск",
                    "Актау",
                    "Темиртау",
                    "Туркестан",
                    "Талдыкорган",
                    "Кокшетау",
                    "Экибастуз",
                    "Рудный",
                    "Жезказган",
                    "Жанаозен",
                    "Балхаш",
                    "Кентау",
                    "Каскелен",
                    "Сатпаев",
                    "Кульсары",
                    "Риддер",
                    "Щучинск",
                    "Степногорск",
                    "Капшагай",
                    "Арыс",
                    "Сарань",
                    "Талгар",
                    "Жаркент",
                    "Аксу",
                    "Байконур (Байконыр)",
                    "Аягоз",
                    "Шахтинск",
                    "Шу",
                    "Алтай",
                    "Лисаковск",
                    "Кандыагаш",
                    "Аксай",
                    "Житикара",
                    "Аральск",
                    "Есик",
                    "Сарыагаш",
                    "Текели",
                    "Каратау",
                    "Атбасар",
                    "Шардара",
                    "Абай",
                    "Аркалык",
                    "Шалкар",
                    "Хромтау",
                    "Ленгер",
                    "Жетысай",
                    "Уштобе",
                    "Жанатас",
                    "Алга",
                    "Шемонаиха",
                    "Макинск",
                    "Ушарал",
                    "Зайсан",
                    "Акколь",
                    "Приозёрск",
                    "Курчатов",
                    "Эмба",
                    "Тайынша",
                    "Сарканд",
                    "Есиль",
                    "Ерейментау",
                    "Серебрянск",
                    "Каркаралинск",
                    "Каражал",
                    "Булаево",
                    "Сергеевка",
                    "Мамлютка",
                    "Шар",
                    "Форт-Шевченко",
                    "Державинск",
                    "Казалинск",
                    "Степняк",
                    "Темир",
                    "Жем"
                ],
                valid: true,
                lazy: false,

                cityRules: [
                    v => !!this.options.includes(v) || 'Город должен быть выбран',
                ],
                priceRules: [
                    v => !!v || 'Цена курса должна присутствовать',
                    v => /^\d+$/.test(v) || 'Значение должно быть цифрой'
                ],
                addressRules: [
                    v => !!v || 'Адрес проведения обучения должен быть выбран'
                ],
                languageRules: [
                    v => !!this.languages.includes(v) || 'Язык обучуния должен быть выбран'
                ],
                languages: [
                    'Казахский',
                    'Английский',
                    'Русский'
                ],
            };
        },
        computed: mapGetters(['getCurrentCourse', 'getInfo']),
        methods: {
            ...mapMutations(['courseCreationProcedureFinished', 'removeFromKeyWords']),
            ...mapActions(['addInfoAction']),
            finishCreateCourse() {
                this.courseCreationProcedureFinished();
                this.$router.push('/courses');
            },
            remove(item) {
                const index = this.getInfo.keyWords.indexOf(item);
                if (index > -1) {
                    this.getInfo.keyWords.splice(index, 1);
                }
            },
            resetForm() {
                this.$refs.form.reset()
            },
            finish(){
                this.$router.push('/profile');
            },
            submitHandler() {

                if (this.$refs.form.validate()) {
                        console.log('add');
                        const data = {
                            courseId: this.getCurrentCourse.id,
                            info: this.getInfo
                        }
                        this.addInfoAction(data);
                }

            },
        },
    }
</script>

<style scoped>
    #price-image{
        height: 20px;
    }
    .price-for-month{
        display: flex;
    }

</style>