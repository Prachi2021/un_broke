SELECT id, description, amount, date, sub_cat_id, subcategory, category
FROM expenses
INNER JOIN (
    SELECT sub_categories.id AS sub_cat_id , sub_categories.subcategory, categories.category
    FROM sub_categories
    JOIN categories
    WHERE sub_categories.catId=categories.id
) AS cats ON expenses.subCatId = cats.sub_cat_id;