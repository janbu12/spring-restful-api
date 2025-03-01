CREATE TABLE `users` (
  `user_id` INT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `namalengkap` VARCHAR(255) NOT NULL,
  `notelepon` VARCHAR(255),
  `alamat` VARCHAR(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;

INSERT INTO `users` (`user_id`, `username`, `password`,`namalengkap`, `notelepon`, `alamat`) VALUES
  (1, 'user1', 'password1', 'User 1', '081234567890', 'Jln. Gunung Agung No 25 Blog F, Bandung'),
  (2, 'user2', 'password2', 'User 2', '081234567891', 'Jln. Tambora No 10 Blog A, Bandung');

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
    `kategori_id` INT AUTO_INCREMENT PRIMARY KEY,
    `nama_kategori` VARCHAR(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;

INSERT INTO `categories` (`kategori_id`, `nama_kategori`) VALUES
  (1, 'tas'),
  (2, 'flysheet'),
  (3, 'tenda'),
  (4, 'jaket'),
  (5, 'kompor'),
  (6, 'kursi lipat'),
  (7, 'matras'),
  (8, 'sepatu'),
  (9, 'sleeping bag');

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
    `produk_id` INT AUTO_INCREMENT PRIMARY KEY,
    `nama_produk` VARCHAR(255) NOT NULL,
    `deskripsi` VARCHAR(350) NOT NULL,
    `stok` INT NOT NULL,
    `harga_sewa` DECIMAL(10, 2) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `gambar` VARCHAR(255),
    `kategori_id` INT,
    FOREIGN KEY (`kategori_id`) REFERENCES `categories`(`kategori_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;

INSERT INTO `products` (`produk_id`, `nama_produk`, `deskripsi`, `stok`, `harga_sewa`, `gambar`, `kategori_id`) VALUES
  (1, 'Tas Sekali Jalan', 'Tas yang bisa dipakai sekali jalan', 10, 250000, '',1),
  (2, 'Flysheet Hiking', 'Flysheet yang cocok digunakan untuk hiking', 5, 150000, '',2);

DROP TABLE IF EXISTS `reviews`;

CREATE TABLE `reviews` (
    `review_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT,
    `produk_id` INT,
    `rating` INT NOT NULL,
    `komentar` TEXT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
    FOREIGN KEY (`produk_id`) REFERENCES `products`(`produk_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;

INSERT INTO `reviews` (`review_id`, `user_id`, `produk_id`, `rating`, `komentar`) VALUES
  (1, 1, 1, 5, 'Sangat bagus'),
  (2, 2, 1, 4, 'Biasa'),
  (3, 1, 2, 5, 'Sangat bagus');

DROP TABLE IF EXISTS `rents`;

CREATE TABLE `rents` (
    `id_peminjaman` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT,
    `tanggal_pinjam` DATE NOT NULL,
    `tanggal_kembali` DATE NOT NULL,
    `status_peminjaman` ENUM('Belum Bayar','Pinjam', 'Dikembalikan') NOT NULL DEFAULT 'Belum Bayar',
    `total_harga` DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;

INSERT INTO `rents` (`user_id`, `tanggal_pinjam`, `tanggal_kembali`, `status_peminjaman`, `total_harga`) VALUES
  (1, '2022-01-01', '2022-01-15', 'Belum Bayar', 1000000),
  (2, '2022-01-16', '2022-01-31', 'Belum Bayar', 450000);

DROP TABLE IF EXISTS `rent_details`;

CREATE TABLE `rent_details` (
    `id_peminjaman` INT,
    `produk_id` INT,
    `jumlah` INT NOT NULL,
    `subtotal` DECIMAL NOT NULL,
    FOREIGN KEY (`produk_id`) REFERENCES `products`(`produk_id`),
    FOREIGN KEY (`id_peminjaman`) REFERENCES `rents`(`id_peminjaman`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `rent_details` (`id_peminjaman`, `produk_id`, `jumlah`, `subtotal`) VALUES
  (4080, 1, 3, 750000),
  (4081, 2, 2, 300000),
  (4080, 1, 1, 250000),
  (4081, 2, 1, 150000);

ALTER TABLE `users`

ADD COLUMN `status` boolean NOT NULL DEFAULT 1;