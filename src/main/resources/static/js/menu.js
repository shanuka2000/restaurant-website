$(document).ready(function () {
    $.getJSON('/json/menu.json', function (data) {
        const cuisinesArr = data.cuisines;
        $.each(cuisinesArr, function (index, item) {
            const cuisineName = item.name;
            const cuisineCategoriesArr = item.categories;

            let cuisineHtml =
                '<ul class="category">' +
                    '<li class="category-heading">' +
                        '<button class="controlBtn">' +
                            '<ion-icon name="chevron-expand-outline"></ion-icon>' +
                            '<span>Our ' + cuisineName + ' Menu</span>' +
                        '</button>' +
                    '</li>' +
                    '<ul class="category-sec">';

            $.each(cuisineCategoriesArr, function (index, subItem) {
                const subCategoryName = subItem.name;
                const foodList = subItem.items;

                let categoryHtml =
                    '<li class="subCategory-heading">' +
                        '<button class="controlBtn">' +
                            '<ion-icon name="chevron-expand-outline"></ion-icon>' +
                            '<span>' + subCategoryName + '</span>' +
                        '</button>' +
                    '</li>' +
                    '<ul class="category-item">';

                $.each(foodList, function (index, foodItem) {
                    const foodName = foodItem.name;
                    const foodPrice = foodItem.price;

                    let foodItemHtml =
                        '<li>' +
                            '<p>' + foodName + '</p>' +
                            '<hr class="hr"/>' +
                            '<p>' + foodPrice +'</p>' +
                        '</li>';

                    categoryHtml += foodItemHtml
                })
                categoryHtml += '</ul>'
                cuisineHtml += categoryHtml;
            })

            cuisineHtml += '</ul></ul>'
            $('.section-menu').append(cuisineHtml);
        })
    })
})