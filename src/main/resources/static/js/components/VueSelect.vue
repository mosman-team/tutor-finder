<template>
    <v-select
        class="style-chooser"
        placeholder="Алматы"
        :options="options"
    >
        <template #no-options="{ search, searching, loading }">
            Такого города нет в списке
        </template>

    </v-select>
</template>

<script>
    export default {
        name: "VueSelect",
        data() {
            return{
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
                ]
            }
        }
    }
</script>

<style>
    .style-chooser .vs__search::placeholder{
        color: #757575;
    }

    .style-chooser .vs__dropdown-toggle{
        padding: 12px;
        border: 1px solid #cfd9db;
        background-color: #ffffff;
        border-radius: 0.25em;
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.08);

    }
    .style-chooser .vs__dropdown-toggle:focus{
        border-color: #2c3e50;
        box-shadow: 0 0 5px rgba(44, 151, 222, 0.2);
    }


</style>

