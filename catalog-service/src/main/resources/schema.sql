CREATE TABLE product (
  uniq_id VARCHAR(50) PRIMARY KEY,
  sku VARCHAR(50),
  name_title TEXT,
  description TEXT,
  list_price VARCHAR(100),
  sale_price VARCHAR(100),
  category VARCHAR(100),
  category_tree TEXT,
  average_product_rating VARCHAR(20),
  product_url TEXT,
  product_image_urls TEXT,
  brand VARCHAR(100),
  total_number_reviews VARCHAR(100),
  reviews TEXT
);