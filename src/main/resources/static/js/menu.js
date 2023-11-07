$(document).ready(function () {
    $.getJSON('/json/menu.json', function (data) {
        const cuisinesArr = data.cuisines;

        // Flags to track if cuisine and category have been displayed
        let cuisineDisplayed = false;
        let categoryDisplayed = false;

        $.each(cuisinesArr, function (index, item) {
            const cuisineName = item.name;
            const cuisineCategoriesArr = item.categories;

            // Display cuisine only once
            if (!cuisineDisplayed) {
                const cuisineHtml =
                    '<ul class="category">' +
                        '<li class="category-heading">' +
                            '<button class="controlBtn">' +
                                '<ion-icon name="chevron-expand-outline"></ion-icon>' +
                                '<span>Our ' + cuisineName + ' Menu</span>' +
                            '</button>' +
                        '</li>' +
                    '<ul class="category-sec">';
                $('.section-menu').append(cuisineHtml);
                cuisineDisplayed = true;
            }

            $.each(cuisineCategoriesArr, function (index, subItem) {
                const subCategoryName = subItem.name;
                const foodList = subItem.items;

                // Display category only once
                if (!categoryDisplayed) {
                    const categoryHtml =
                        '<li class="subCategory-heading">' +
                            '<button class="controlBtn">' +
                                '<ion-icon name="chevron-expand-outline"></ion-icon>' +
                                '<span>' + subCategoryName + '</span>' +
                            '</button>' +
                        '</li>' +
                        '<ul class="category-item">';
                    $('.category-sec').append(categoryHtml);
                    categoryDisplayed = true;
                }

                $.each(foodList, function (index, foodItem) {
                    const foodName = foodItem.name;
                    const foodPrice = foodItem.price;

                    const htmlString =
                        '<li>' +
                            '<p>' + foodName + '</p>' +
                            '<hr class="hr"/>' +
                            '<p>' + foodPrice + '</p>' +
                        '</li>' +
                        '</ul></ul>'
                    $('.category-item').append(htmlString);
                })
            })
        })
    })
})





