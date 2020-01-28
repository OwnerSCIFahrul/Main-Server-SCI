# Format Placeholders

## Nukkit
- %has_pemission<permission>% - mengembalikan apakah pemain memiliki izin tertentu
- %player_item_in_hand_id% - mengembalikan id dari item yang ditahan
- %player_item_in_hand_meta% - mengembalikan meta dari item yang ditahan
  
## EconomyAPI
- %economy_money% - mengembalikan saldo pemain saat ini

## Factions
- %factions_faction% - mengembalikan nama tim pemain saat ini
  
## LuckPerms
- %luckperms_prefix% - Mengembalikan awalan pemain.
- %luckperms_suffix% - Mengembalikan akhiran pemain.
- %luckperms_meta_<meta key>% - Mengembalikan nilai yang terkait dengan kunci meta yang diberikan.
- %luckperms_prefix_element_<element>% - Mengembalikan nilai elemen awalan untuk pemain. Elemen yang sah tercantum di sini.
- %luckperms_suffix_element_<element>% - Mengembalikan nilai elemen akhiran untuk pemain. Elemen yang sah tercantum di sini.
- %luckperms_context_<context key>% - Mengembalikan nilai kunci konteks yang diberikan, atau string kosong jika konteksnya tidak ditetapkan.
- %luckperms_groups% - Mengembalikan daftar semua kelompok yang diwarisi oleh pemain, dipisahkan dengan koma
- %luckperms_primary_group_name% - Mengembalikan nama kelompok utama pemain
- %luckperms_has_permission_<permission>% - Memeriksa apakah pemain memiliki izin yang diberikan secara langsung. Tidak memperhitungkan wildcard, atau izin yang diwarisi.
- %luckperms_inherits_permission_<permission>% - Memeriksa apakah pemain memiliki atau mewarisi izin yang diberikan. Tidak memperhitungkan wildcard, atau izin yang diwarisi. Tempat penampung check_permission lebih disukai daripada tempat penampung ini.
- %luckperms_check_permission_<permission>% - Memeriksa untuk melihat apakah pemain memiliki izin yang diberikan. Ini dilakukan dengan cara yang sama plugin akan memeriksa izin.
- %luckperms_in_group_<group>% - Mengembalikan jika pemain adalah anggota kelompok tertentu. Tidak termasuk kelompok turunan.
- %luckperms_inherits_group_<group>% - Mengembalikan jika pemain berada dalam atau mewarisi kelompok yang diberikan.
- %luckperms_on_track_<track>% - Mengembalikan jika kelompok utama pemain ada di trek yang diberikan.
- %luckperms_has_groups_on_track_<track>% - Kembali jika pemain mewarisi dari kelompok mana pun di trek yang diberikan
- %luckperms_highest_group_by_weight% - Mengembalikan nama kelompok prioity tertinggi pemain.
- %luckperms_lowest_group_by_weight% - Mengembalikan nama kelompok dengan prioritas terendah pemain.
- %luckperms_first_group_on_tracks_<tracks>% - Mengembalikan nama kelompok pertama yang dimiliki pemain di trek yang diberikan. Trek mewakili daftar trek yang dipisahkan koma. Setiap kelompok di trek dianggap berurutan.
- %luckperms_last_group_on_tracks_<tracks>% - Mengembalikan nama kelompok terakhir yang dimiliki pemain di trek yang diberikan. Trek mewakili daftar trek yang dipisahkan koma. Setiap kelompok di trek dianggap dalam urutan terbalik.
- %luckperms_expiry_time_<permission>% - Mendapatkan waktu hingga izin yang diberikan akan kedaluwarsa untuk pemain. Mengembalikan kosong jika pemain tidak memiliki izin.
- %luckperms_inherited_expiry_time_<permission>% - Mendapatkan waktu hingga izin yang diberikan akan kedaluwarsa untuk pemain. Mengembalikan kosong jika pemain tidak memiliki izin. Placeholders ini juga akan memeriksa izin bawaan untuk kecocokan.
- %luckperms_group_expiry_time_<group name>% - Mendapat waktu hingga keanggotaan kelompok yang diberikan akan kedaluwarsa untuk pemain. Mengembalikan kosong jika pemain tidak memiliki kelompok.

Namun variabel harus dimasukkan dalam gaya PlaceholderAPI, misalnya %luckperms_meta<meta key>%
  
## MultiPerms
- %multiperms_prefix% - mengembalikan awalan pemain
- %multiperms_suffix% - mengembalikan akhiran pemain
- %multiperms_group% - mengembalikan kelompok pemain utama
- %multiperms_groups% - mengembalikan semua kelompok pemain
- %multiperms_group_prefix<group>% - mengembalikan awalan kelompok
- %multiperms_group_suffix<group>% - mengembalikan akhiran kelompok
- %multiperms_groups_priority<group>% - mengembalikan prioritas kelompok

## Residence
- %res_user_current_owner% - mengembalikan nama pemilik tempat tinggal saat ini
- %res_user_current_rent_ends% - mengembalikan waktu ketika sewa berakhir
- %res_user_current_rent_days% - mengembalikan jumlah hari Anda dapat menyewa daerah
- %res_user_current_rented_by% - mengembalikan nama pemain yang menyewa daerah
- %res_user_current_rent_price% - mengembalikan harga daerah jika disewakan
- %res_user_current_for_rent% - mengembalikan benar atau salah jika daerah disewakan
- %res_user_current_sale_price% - mengembalikan harga jual
- %res_user_current_for_sale% - mengembalikan benar atau salah jika daerah dijual
- %res_user_current_bank% - mengembalikan jumlah bank tempat tinggal
- %res_user_current% - mengembalikan nama tempat tinggal saat ini
- %res_user_block_cost% - mengembalikan pemain memblokir biaya pembelian
- %res_user_max_rentables% - mengembalikan pemain yang dapat dimiliki oleh pemain yang dapat disewa secara maksimal
- %res_user_max_sub_depth% - mengembalikan kedalaman subzone maksimum yang diizinkan
- %res_user_max_zones% - mengembalikan jumlah pemain maksimum yang bisa dimiliki oleh subzone
- %res_user_max_h% - Mengembalikan ukuran maksimal tinggi
- %res_user_max_l% - Mengembalikan ukuran panjang maksimal
- %res_user_max_w% - mengembalikan ukuran lebar maksimal
- %res_user_can_create% - mengembalikan benar atau salah jika pemain dapat membuat tempat tinggal
- %res_user_amount% - mengembalikan jumlah tempat tinggal yang dimiliki pemain
- %res_user_admin% - mengembalikan true atau false tergantung apakah pemain res admin atau tidak
- %res_user_group% - mengembalikan kelompok tempat tinggal pemain
  
  
## SynapseAPI
- %synapse_server% - mengembalikan deskripsi server sinaps saat ini
- %synapse_servers% - mengembalikan daftar semua server yang terhubung ke nemisys
- %synapse_players<server>% - mengembalikan jumlah pemain aktual di server target
- %synapse_max_players<server>% - mengembalikan jumlah pemain maksimal pada server target
- %synapse_status<server>% - status pengembalian server (online/offline) - nilai dapat dimodifikasi oleh parameter "true" dan "false"
  
## KDR
- kdr_deaths - kematian pemain
- kdr_kills - membunuh pemain
- kdr_kdr - kdr pemain