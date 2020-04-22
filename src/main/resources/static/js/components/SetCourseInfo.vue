<template>
    <v-form ref="form" @submit.prevent="submitHandler" v-model="valid" :lazy-validation="lazy">
        <v-card class="pa-2">
            <v-card-text>
                <div>
                    <v-autocomplete
                            v-model="getCurrentCourse.city"
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
                            v-model="getCurrentCourse.address"
                            required
                            :rules="addressRules"
                    >
                    </v-text-field>
                </div>

                <div class="price-for-month">
                    <img src="/static/img/tenge.png" alt="" id="price-image">
                    <v-text-field
                            v-model="getCurrentCourse.price"
                            label="Цена за Месяц"
                            placeholder="0"
                            required
                            :rules="priceRules"
                    ></v-text-field>
                </div>

                <div>
                    <header>Язык обучения</header>
                    <v-radio-group class="ma-1"
                                   v-model="getCurrentCourse.language"
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
                    <!--<v-combobox
                            v-model="getCurrentCourse.keyWords"
                            :items="items"
                            chips
                            clearable
                            label="key words"
                            multiple
                            prepend-icon="filter_list"
                            solo
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
                    </v-combobox>-->

                    <v-select
                            v-model="getCurrentCourse.keyWords"
                            :items="items"
                            chips
                            clearable
                            label="Choose course category"
                            multiple
                            prepend-icon="filter_list"
                            solo
                    >
                        <template v-slot:selection="{ attrs, item, select, selected }">
                            <v-chip
                                    v-bind="attrs"
                                    :input-value="selected"
                                    @click="select"
                                    @click:close="remove(item)"
                            >
                                <strong>{{ item }}</strong>&nbsp;
                            </v-chip>
                        </template>
                    </v-select>

                </div>
            </v-card-text>
            <v-card-actions>
                <v-btn color="error" text @click="resetForm">Reset Form</v-btn>
                <v-spacer></v-spacer>
                <v-btn color="primary" text type="submit" :disabled="!valid">Submit</v-btn>
            </v-card-actions>

        </v-card>
    </v-form>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex';

    export default {
        name: "SetCourseInfo",
        components: {},

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
                items: [
                    'Business & Management',
                    'Creative Arts & Media',
                    'Healthcare & Medicine',
                    'History',
                    'IT & Computer Science',
                    'Language',
                    'Law',
                    'Literature',
                    'Nature & Environment',
                    'Politics & Society',
                    'Psychology & Mental Health',
                    'Science, Engineering & Maths',
                    'Study Skills',
                    'Teaching'
                ],

            };
        },
        computed: mapGetters(['getCurrentCourse']),
        methods: {
            ...mapMutations(['removeFromKeyWordsMutation']),
            ...mapActions(['addInfoAction']),
            finishCreateCourse() {
                this.$router.push('/courses');
            },
            remove(item) {
                const index = this.getCurrentCourse.keyWords.indexOf(item);
                if (index > -1) {
                    this.getCurrentCourse.keyWords.splice(index, 1);
                }
            },
            resetForm() {
                this.$refs.form.reset()
            },
            submitHandler() {

                if (this.$refs.form.validate()) {

                        const data = {
                            courseId: this.getCurrentCourse.id,
                            info : {
                                city: this.getCurrentCourse.city,
                                address: this.getCurrentCourse.address,
                                price: this.getCurrentCourse.price,
                                language: this.getCurrentCourse.language,
                                keyWords: this.getCurrentCourse.keyWords
                            }
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