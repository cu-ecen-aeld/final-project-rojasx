require recipes-core/images/core-image-base.bb

CORE_IMAGE_EXTRA_INSTALL += "openssh openssl openssh-sftp-server"
CORE_IMAGE_EXTRA_INSTALL += "ethtool"
CORE_IMAGE_EXTRA_INSTALL += "python3"
CORE_IMAGE_EXTRA_INSTALL += "rpi-gpio"
CORE_IMAGE_EXTRA_INSTALL += "forceapp"

inherit extrausers
PASSWD = "\$5\$2WoxjAdaC2\$l4aj6Is.EWkD72Vt.byhM5qRtF9HcCM/5YpbxpmvNB5"
EXTRA_USERS_PARAMS = "usermod -p '${PASSWD}' root;"

# Add a dependency on the network configuration task
# do_rootfs[depends] += "virtual/kernel:do_deploy"
#
# ROOTFS_POSTPROCESS_COMMAND += "configure_end0; "
# 
# configure_end0() {
#     
#     cat >/etc/network/interfaces <<EOF
#     auto end0
#     iface end0 inet static
#         address 192.168.0.13
#         netmask 255.255.255.0
# }
